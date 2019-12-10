package com.example.lewislovette_7814291;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import android.content.Context;
import android.provider.ContactsContract.Data;
import android.view.View;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class UsersModelTest {

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();


  @Test
  public void getInstance() {
    UsersModel usersModel = UsersModel.getInstance();

    assertEquals(usersModel.getEmail(), null);
  }

  @Test
  public void setView() {}

  @Test
  public void hasPicture() {}

  @Test
  public void getProfilePic() {}

  @Test
  public void setProfilePic() {}

  @Test
  public void addUser() {
  }

  @Test
  public void exists() {}

  @Test
  public void getEmail() {
    UsersModel usersModel = UsersModel.getInstance();
    usersModel.setEmail("testemail@email.com");

    assertEquals(usersModel.getEmail(), "testemail@email.com");
  }

  @Test
  public void setEmail() {
    UsersModel usersModel = UsersModel.getInstance();
    usersModel.setEmail("testemail@email.com");

    assertEquals(usersModel.getEmail(), "testemail@email.com");
  }

  @Test
  public void getPassword() {
    UsersModel usersModel = UsersModel.getInstance();
    usersModel.setPassword("password");

    assertEquals(usersModel.getPassword(), "password");
  }

  @Test
  public void setPassword() {
    UsersModel usersModel = UsersModel.getInstance();
    usersModel.setPassword("password");

    assertEquals(usersModel.getPassword(), "password");
  }

}