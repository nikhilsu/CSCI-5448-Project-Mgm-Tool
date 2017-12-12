package com.prorg.service.impl;

import com.prorg.helper.result.Response;
import com.prorg.helper.result.ValidationResponse;
import com.prorg.helper.validator.ModelValidator;
import com.prorg.model.Storyboard;
import com.prorg.dao.StoryboardDao;
import com.prorg.model.User;
import com.prorg.service.StoryboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StoryboardServiceImpl implements StoryboardService {

    private final StoryboardDao storyboardDao;
    private final ModelValidator validator;

    @Autowired
    public StoryboardServiceImpl(StoryboardDao storyboardDao, ModelValidator validator) {
        this.storyboardDao = storyboardDao;
        this.validator = validator;
    }


    @Override
    public Response<Integer> createStoryboard(String title, String description, User createdBy){
        Storyboard storyBoardToAdd = new Storyboard();
        storyBoardToAdd.setTitle(title)
                       .setDescription(description)
                       .setCreatedBy(createdBy);
        ValidationResponse validationResponse = validator.validate(storyBoardToAdd);
        if (!validationResponse.isValid()) {
            return Response.Failure(validationResponse.errors());
        }
        return storyboardDao.save(storyBoardToAdd);
    }

    @Override
    public Response<Storyboard> getStoryboardById(int storyboardId) throws Exception {
        Response<Storyboard> queryResponse = storyboardDao.findById(storyboardId);
        return Response.Success(queryResponse.data());
    }

    @Override
    public Response updateUsersWhoHaveAccessToStoryboard(int storyboardId, ArrayList<User> usersWhoHaveAccess) throws Exception {
        Response queryResponse = storyboardDao.findById(storyboardId);
        if (!queryResponse.isSuccessful())
            return queryResponse;
        Storyboard storyboard = (Storyboard) queryResponse.data();
        storyboard.setUsersWhoHaveAccess(usersWhoHaveAccess);
        return storyboardDao.update(storyboard);
    }

    @Override
    public Response<List<Storyboard>> getStoryboardGivenItsCreator(User creator) throws Exception {
        Response<List<Storyboard>> response = storyboardDao.findByCreator(creator);
        if (!response.isSuccessful())
            return response;
        return Response.Success(response.data());
    }
}
