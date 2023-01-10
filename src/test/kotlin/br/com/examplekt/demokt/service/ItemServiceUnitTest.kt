package br.com.examplekt.demokt.service

import br.com.examplekt.demokt.domain.entity.ItemEntity
import br.com.examplekt.demokt.domain.repository.ItemRepository
import io.mockk.*
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*

class ItemServiceUnitTest() {

    private val itemRepository: ItemRepository = mockk()

    private val itemService = ItemService(itemRepository)

    @Test
    fun whenGetItemById_thenReturnItem(){
        val itemEntity = ItemEntity(1, "test")
        every { itemRepository.findById(1) } returns Optional.of(itemEntity)

        val result = itemService.findById(1)

        verify(exactly = 1) { itemRepository.findById(1) }
        Assert.assertEquals(itemEntity, result)
    }

    @Test
    fun whenGetItemById_thenReturnIllegalArgumentException(){
        Assertions.assertThrows(
            IllegalArgumentException::class.java
        ) {
            every { itemRepository.findById(1) } returns Optional.empty()

            itemService.findById(1)
        }
    }

    @Test
    fun whenDeleteItemById_thenDelete(){
        every { itemRepository.deleteById(1) } just runs

        itemService.deleteById(1)

        verify(exactly = 1) { itemRepository.deleteById(1) }
    }

    @Test
    fun whenSave_thenSaveItem(){
        val itemEntity = ItemEntity(1, "test")
        every { itemRepository.save(itemEntity) } returns itemEntity

        itemService.save(itemEntity)

        verify(exactly = 1) { itemRepository.save(itemEntity) }
    }

    @Test
    fun whenUpdate_thenUpdateItem(){
        val existedItem = ItemEntity(1, "test")
        val updateItem = ItemEntity(1, "test update")
        every { itemRepository.findById(any()) } returns Optional.of(existedItem)
        every { itemRepository.save(any()) } returns updateItem

        itemService.update(1, updateItem)

        verify(exactly = 1) { itemRepository.findById(1) }
        verify(exactly = 1) { itemRepository.save(existedItem) }
    }

    @Test
    fun whenFindAll_thenReturnSearch(){
        val itemEntity = ItemEntity(1, "test")
        val pageRequest = PageRequest.of(0, 10, Sort.by("id"))
        every { itemRepository.findAll(pageRequest) } returns PageImpl(listOf(itemEntity));

        itemService.findAll(pageRequest)

        verify(exactly = 1) { itemRepository.findAll(pageRequest)}
    }

}