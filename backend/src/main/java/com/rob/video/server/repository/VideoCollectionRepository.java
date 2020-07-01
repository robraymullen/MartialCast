package com.rob.video.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rob.video.server.model.VideoCollection;

public interface VideoCollectionRepository extends JpaRepository<VideoCollection, Long>, IVideoPropertyRepository {

	List<VideoCollection> findAllByOrderByNameDesc();

}
