package com.example.chat.dao;

import com.example.chat.dao.repository.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRepositoryTest {

  @MockBean
  private MessageRepository messageRepository;

  @Test
  public void testMessageRepository() {

    Assert.assertNotNull(messageRepository.oldMessage(1L, 2L));
  }

}
