package com.rob.video.server.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rob.video.server.model.Video;


@Repository
@Transactional
public class VideoSearchRepository<FullTextEntityManager> {

	// Spring will inject here the entity manager object
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * A basic search for the entity User. The search is done by exact match per
	 * keywords on fields name, city and email.
	 * 
	 * @param text
	 *            The query text.
	 */
	public List<Video> search(String text) {

		// get the full text entity manager
		org.hibernate.search.jpa.FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(entityManager);

		// create the query using Hibernate Search query DSL
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Video.class)
				.get();

		// a very basic query by keywords
		org.apache.lucene.search.Query query = queryBuilder.keyword().onFields("name", "description").matching(text)
				.createQuery();

		// wrap Lucene query in an Hibernate Query object
		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Video.class);

		// execute search and return results (sorted by relevance as default)
		@SuppressWarnings("unchecked")
		List<Video> results = jpaQuery.getResultList();

		return results;
	}

}