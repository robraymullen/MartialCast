package com.rob.video.server.repository;

import com.rob.video.server.model.IVideoProperty;

public interface IVideoPropertyRepository  {
	
	IVideoProperty findByNameIgnoreCase(String name);

}
