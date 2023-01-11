package br.com.examplekt.demokt.service

import br.com.examplekt.demokt.domain.entity.ItemEntity
import br.com.examplekt.demokt.domain.repository.ItemRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {

    fun save(itemEntity: ItemEntity) = itemRepository.save(itemEntity)

    fun findAll(pageRequest: PageRequest) = itemRepository.findAll(pageRequest)

    fun deleteById(id: Int){
        itemRepository.deleteById(id)
    }

    fun findById(id: Int): ItemEntity = itemRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Item Not Found") }

    fun update(id: Int, itemEntity: ItemEntity): ItemEntity{
        val existedItem = findById(id)
        existedItem.name = itemEntity.name
        return save(existedItem)
    }

}