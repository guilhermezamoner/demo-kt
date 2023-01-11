package br.com.examplekt.demokt.resource

import br.com.examplekt.demokt.domain.entity.ItemEntity
import br.com.examplekt.demokt.resource.dto.ItemDTO
import br.com.examplekt.demokt.service.ItemService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import org.apache.logging.log4j.kotlin.logger

@RestController
@RequestMapping("items")
class ItemController(
    private val itemService: ItemService
) {

    private val logger = logger()

    @GetMapping
    fun getItems(
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
        @RequestParam(value = "sortBy", required = false, defaultValue = "id") sortBy: String
    ): Page<ItemEntity> {
        logger.info("getItems - start - page: [$page], size: [$size], sortBy: [$sortBy]")
        val pageable = PageRequest.of(
            page, size, Sort.by(sortBy)
        )
        return itemService.findAll(pageable)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<ItemDTO>{
        logger.info("getById - start - id: [$id]")
        return ResponseEntity.ok(itemService.findById(id).toItemDTO())
    }

    @PostMapping
    fun createItem(@Valid @RequestBody item: ItemDTO): ResponseEntity<URI> {
        logger.info("createItem - start - item: [$item]")
        val createdItem = itemService.save(item.toItemEntity())
        return ResponseEntity.created(ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdItem.id)
            .toUri()).build()
    }

    @PutMapping("/{id}")
    fun updateItem(@RequestBody item: ItemDTO, @PathVariable("id") id: Int): ResponseEntity<ItemDTO>{
        logger.info("updateItem - start - item: [$item], id: [$id]")
        val updatedItem = itemService.update(id, item.toItemEntity())
        return ResponseEntity.ok(updatedItem.toItemDTO())
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int): ResponseEntity<Void> {
        logger.info("deleteById - start - id: [$id]")
        itemService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}