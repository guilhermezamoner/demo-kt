package br.com.examplekt.demokt.resource

import br.com.examplekt.demokt.domain.entity.ItemEntity
import br.com.examplekt.demokt.resource.dto.ItemDTO
import br.com.examplekt.demokt.service.ItemService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("items")
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping
    fun getItems(
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
        @RequestParam(value = "sortBy", required = false, defaultValue = "id") sortBy: String
    ): Page<ItemEntity> {
        val pageable = PageRequest.of(
            page, size, Sort.by(sortBy)
        )
        return itemService.findAll(pageable)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<ItemDTO>{
        return ResponseEntity.ok(itemService.findById(id).toItemDTO())
    }

    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): ResponseEntity<URI> {
        val createdItem = itemService.save(item.toItemEntity())
        return ResponseEntity.created(ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdItem.id)
            .toUri()).build()
    }

    @PutMapping("/{id}")
    fun updateItem(@RequestBody item: ItemDTO, @PathVariable("id") id: Int): ResponseEntity<ItemDTO>{
        val updatedItem = itemService.update(id, item.toItemEntity())
        return ResponseEntity.ok(updatedItem.toItemDTO())
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int): ResponseEntity<Void> {
        itemService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}