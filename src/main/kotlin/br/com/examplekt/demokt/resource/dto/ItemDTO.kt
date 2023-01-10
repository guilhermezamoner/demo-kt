package br.com.examplekt.demokt.resource.dto

import br.com.examplekt.demokt.domain.entity.ItemEntity

data class ItemDTO(
    val id: Int?,
    val name: String
) {
    fun toItemEntity(): ItemEntity{
        return ItemEntity(
            name = name
        )
    }
}
