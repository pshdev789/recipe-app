package com.test.recipe;

import static org.assertj.core.api.Assertions.assertThat;

import com.ass.recipe.RecipeAppApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = RecipeAppApplication.class)
class RecipeAppApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		// Ensure the application context is loaded
		assertThat(applicationContext).isNotNull();
	}
}
