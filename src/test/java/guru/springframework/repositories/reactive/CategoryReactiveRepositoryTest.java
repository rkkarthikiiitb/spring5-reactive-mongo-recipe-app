package guru.springframework.repositories.reactive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Category;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {
	
	@Autowired
	CategoryReactiveRepository categoryReactiveRepository;

	@Before
	public void setUp() throws Exception {
		
		categoryReactiveRepository.deleteAll().block();
	}

	@Test
	public void testCategorySave() {
		Category cat1 = new Category();
		cat1.setDescription("Malysian");
		categoryReactiveRepository.save(cat1).block();
		Long catCount = categoryReactiveRepository.count().block();
		assertEquals(Long.valueOf(1L), catCount);
		
	}
	
	@Test
	public void testCategoryFindByDesc() {
		Category cat2 = new Category();
		cat2.setDescription("Spicy");
		categoryReactiveRepository.save(cat2).block();
		Category fetchedDesc = categoryReactiveRepository.findByDescription("Spicy").block();
		assertNotNull(fetchedDesc.getId());
	}

}
