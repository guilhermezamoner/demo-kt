package br.com.examplekt.demokt.domain.entity

import br.com.examplekt.demokt.resource.dto.ItemDTO
import jakarta.persistence.*

@Entity
@Table(name = "items")
class ItemEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?= null,
    var name: String
) {
    fun toItemDTO(): ItemDTO{
        return ItemDTO(
            id = id,
            name = name
        )
    }

}