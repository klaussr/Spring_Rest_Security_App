package com.semkin.spring_rest_security_app.service.impl;

import com.semkin.spring_rest_security_app.model.Event;
import com.semkin.spring_rest_security_app.model.File;
import com.semkin.spring_rest_security_app.repository.EventRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventServiceImplTest {
    private static final Long TEST_EVENT_ID = 1L;
    @Mock
    EventRepository eventRepositoryMock;
    @Mock
    EventServiceImpl eventServiceMock;

    @InjectMocks
    Event testEvent;
    Event testEvent1;
    List<Event> testDevelopersList;

    @Captor
    ArgumentCaptor<Integer> idCaptor;


    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(eventRepositoryMock);
        testEvent = new Event();
        testEvent.setId(TEST_EVENT_ID);
        File file = new File();
        file.setId(4L);
        file.setFileName("Bronx");
        file.setLocation("C:\\ProgramFiles");
        testEvent.setFile(file);

        testEvent1 = new Event();
        testEvent1.setId(TEST_EVENT_ID +1);
        File file1 = new File();
        file1.setId(5L);
        file1.setFileName("Vancuver");
        file1.setLocation("C:\\Users");
        testEvent1.setFile(file1);

        testDevelopersList = Arrays.asList(testEvent, testEvent1);
    }

    @Test
    void create()  {
        doReturn(testEvent).when(eventRepositoryMock).save(testEvent);
        eventRepositoryMock.save(testEvent);
        assertEquals(testEvent.getFile().getFileName(), "Bronx");
        assertEquals(testEvent.getId(), 1);
        assertEquals(testEvent.getFile().getLocation(), "C:\\ProgramFiles");
    }

    @Test
    void deleteById() {
        eventRepositoryMock.deleteById(TEST_EVENT_ID);
        Mockito.verify(eventRepositoryMock, times(1)).deleteById(anyLong());
        Mockito.verify(eventRepositoryMock).deleteById(Long.valueOf(idCaptor.capture()));
        assertEquals(TEST_EVENT_ID, idCaptor.getValue());
    }

    @Test
    void update() {
        when(eventServiceMock.update(testEvent)).thenReturn(testEvent);
        assertEquals(testEvent, eventServiceMock.update(testEvent));
    }

    @Test
    void getById() {
        when(eventServiceMock.getById(TEST_EVENT_ID)).thenReturn(testEvent);
        assertEquals(testEvent, eventServiceMock.getById(TEST_EVENT_ID));
        Mockito.verify(eventServiceMock).getById(TEST_EVENT_ID);

    }

    @Test
    void getAll() {
        when(eventServiceMock.getAll()).thenReturn(testDevelopersList);
        List<Event> currentList = eventServiceMock.getAll();
        Mockito.verify(eventServiceMock).getAll();
        assertEquals(testDevelopersList, currentList);
    }
}