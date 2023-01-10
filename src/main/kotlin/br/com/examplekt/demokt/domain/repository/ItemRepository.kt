package br.com.examplekt.demokt.domain.repository

import br.com.examplekt.demokt.domain.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemEntity, Int> {
}