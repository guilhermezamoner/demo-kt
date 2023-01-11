package br.com.examplekt.demokt.resource.dto

import br.com.examplekt.demokt.domain.entity.ItemEntity
import jakarta.validation.constraints.NotBlank

data class ItemDTO(
    val id: Int?,
    @NotBlank
    val name: String
) {
    fun toItemEntity(): ItemEntity{
        return ItemEntity(
            name = name
        )
    }
}
