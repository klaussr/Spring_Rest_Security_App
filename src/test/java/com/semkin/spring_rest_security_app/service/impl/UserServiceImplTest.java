package com.semkin.spring_rest_security_app.service.impl;

import com.semkin.spring_rest_security_app.model.User;
import com.semkin.spring_rest_security_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    private static final Long TEST_SPECIALTY_ID = 1L;

    @Mock
    UserRepository userRepository;
    @Mock
    UserServiceImpl serviceUnderTest;

    @InjectMocks
    User testUser;
    User testUser1;
    List<User> testListUsers;

    @Captor
    ArgumentCaptor<Integer> idCaptor;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(userRepository);
        testUser = new User("test", "test@mail.ru", "testpass", new ArrayList<>(), new ArrayList<>());
        testUser.setId(TEST_SPECIALTY_ID);

        testUser1 = new User("test1", "test1@mail.ru", "testpass1", new ArrayList<>(), new ArrayList<>());        testUser1.setId(TEST_SPECIALTY_ID+1);
        testUser1.setId(TEST_SPECIALTY_ID+1);

        testListUsers = Arrays.asList(testUser, testUser1);
    }

    @Test
    void getById() {
        when(serviceUnderTest.getById(TEST_SPECIALTY_ID)).thenReturn(testUser);
        assertEquals(testUser, serviceUnderTest.getById(TEST_SPECIALTY_ID));
        Mockito.verify(serviceUnderTest).getById(TEST_SPECIALTY_ID);
    }

    @Test
    void getAll() {
        when(serviceUnderTest.getAll()).thenReturn(testListUsers);
        List<User> currentList = serviceUnderTest.getAll();
        Mockito.verify(serviceUnderTest).getAll();
        assertEquals(testListUsers, currentList);
    }

    @Test
    void create() {
        doReturn(testUser).when(serviceUnderTest).create(testUser);
        serviceUnderTest.create(testUser);
        assertEquals(testUser.getUserName(), "test");
    }

    @Test
    void update() {
        when(serviceUnderTest.update(testUser)).thenReturn(testUser);
        assertEquals(testUser, serviceUnderTest.update(testUser));
    }

    @Test
    void deleteById() {
        userRepository.deleteById(TEST_SPECIALTY_ID);
        Mockito.verify(userRepository, times(1)).deleteById(anyLong());
        Mockito.verify(userRepository).deleteById(Long.valueOf(idCaptor.capture()));
        assertEquals(TEST_SPECIALTY_ID, idCaptor.getValue());
    }
}