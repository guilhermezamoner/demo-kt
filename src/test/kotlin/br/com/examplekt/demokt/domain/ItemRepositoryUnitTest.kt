package br.com.examplekt.demokt.domain

import br.com.examplekt.demokt.domain.entity.ItemEntity
import br.com.examplekt.demokt.domain.repository.ItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class ItemRepositoryUnitTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var itemRepository: ItemRepository

    @Test
    fun whenFindById_thenReturnItem(){
        val itemEntity = ItemEntity(null, "test")

        entityManager.persist(itemEntity)
        entityManager.flush()

        val itemFound = itemRepository.findById(1).get()

        assertEquals(itemFound, itemEntity)
    }

}