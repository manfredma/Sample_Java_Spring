package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreeterServiceIntegrationTests {

  @Autowired
  private GreeterService greeterService;

  @Test
  public void sayHello_whenGivenDuke_thenReturnsHelloWorldDuke() {

    // Given
    String caller = "Duke";

    // When
    String greeting = greeterService.sayHello(caller);

    // Then
    assertThat(greeting).isEqualTo("Hello World, Duke");

  }
}