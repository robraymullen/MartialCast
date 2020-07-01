const videoRoot : string = "videos";
const serverRoot : string = "http://localhost:8080/";
const localRoot : string = "http://localhost:4200/";
export const APIConfig = {
    VIDEO_ROOT : serverRoot+videoRoot,
    VIDEO_FILE : serverRoot+videoRoot+"/videosrc/",
    VIDEO_PREVIEW_FILE_ROOT : serverRoot+videoRoot+"/videosrc/preview/",
    SINGLE_VIDEO : {
        URL : serverRoot +videoRoot+"/"
    },
    HOME : {
        URL : serverRoot+"videos?size=5"
    },
    SEARCH : {
        URL : serverRoot + "videos/search?q="
    },
    RELATED : {
        STYLE : {
            URL : serverRoot+videoRoot+"/style?q="
        },
        TECHNIQUES :{
            URL : serverRoot+videoRoot+"/technique?q="
        },
        INSTRUCTORS : {
            URL : serverRoot+videoRoot+"/instructor?q="
        },
        COLLECTION : {
            URL : serverRoot+videoRoot+"/collection?q="
        }
    },
    STYLES : {
        URL : localRoot+"style/",
        API : {
            URL : serverRoot +  videoRoot + "/style?q="
        }
    },
    INSTRUCTORS : {
        URL : localRoot + "instructor/",
        API : {
            URL : serverRoot + videoRoot + "/instructor?q="
        }
    },
    TAGS : {
        URL : localRoot + "tag/",
        API : {
            URL : serverRoot + videoRoot + "tag?q="
        }
    },
    COLLECTION : {
        URL : localRoot + "collection/",
        API : {
            URL : serverRoot + videoRoot + "/collection?q="
        }
    },
    TECHNIQUES :{
        API : {
            URL : serverRoot + videoRoot +"/technique?q="
        }
    },
    PROPERTY_NAME_LIST :{
        COLLECTIONS : {
            URL : serverRoot + "collections?size=5"
        },
        INSTRUCTORS : {
            URL : serverRoot + "instructors?size=5"
        },
        STYLES : {
            URL : serverRoot +"styles?size=5"
        },
        TECHNIQUES : {
            URL : serverRoot+"techniques?size=5"
        },
        TAGS : {
            URL : serverRoot+"tags?size=5"
        }

    }
}

/**
 * private recentURL : string = "http://localhost:8080/videos?size=5";
    private tagURL : string = "http://localhost:8080/tags?size=5";
    private styleURL : string = "http://localhost:8080/styles?size=5";
    private instructorURL : string = "http://localhost:8080/instructors?size=5";
    private collectionURL : string = "http://localhost:8080/collections?size=5";
    private videoURL : string = "http://localhost:8080/videos/";
    private searchURL : string = "http://localhost:8080/videos/search?q=";
    private relatedByStyleURL : string = "http://localhost:8080/videos/style?q=";
    private relatedByTechniqueURL : string = "http://localhost:8080/videos/technique?q=";
    private relatedByInstructorURL : string = "http://localhost:8080/videos/instructor?q=";
    private relatedByCollectionURL : string = "http://localhost:8080/videos/collection?q=";
    private videosByInstructorURL : string = "http://localhost:8080/videos/instructor?q=";
    private videosByStyleURL : string = "http://localhost:8080/videos/style?q=";
    private videosByTag : string = "http://localhost:8080/videos/tag?q=";
    private videosByCollection : string = "http://localhost:8080/videos/collection?q=";
 */