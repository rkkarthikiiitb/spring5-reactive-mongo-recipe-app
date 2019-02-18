package guru.springframework.repositories.reactive;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

	@Autowired
	UnitOfMeasureReactiveRepository uomReactiveRepository;

	@Before
	public void setUp() throws Exception {
		
		uomReactiveRepository.deleteAll().block();

	}

	@Test
	public void testUomSave() {
		UnitOfMeasure uom  = new UnitOfMeasure();
		uom.setDescription("ounce");
		uomReactiveRepository.save(uom).block();
		Long count = uomReactiveRepository.count().block();
		assertEquals(Long.valueOf(1L), count);
	}
	
	@Test
	public void testUomFind() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription("grams");
		uomReactiveRepository.save(uom).block();
		UnitOfMeasure fetchUom = uomReactiveRepository.findByDescription("grams").block();
		assertNotNull(fetchUom.getId());
	}

}
