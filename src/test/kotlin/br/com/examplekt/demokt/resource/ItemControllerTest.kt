package br.com.examplekt.demokt.resource

import br.com.examplekt.demokt.domain.entity.ItemEntity
import br.com.examplekt.demokt.service.ItemService
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest
class ItemControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var itemService: ItemService

    @Test
    fun givenExistingItem_whenGetRequest_thenReturnItemJsonWithStatusOk(){
        val item =  ItemEntity(1, "test")
        every { itemService.findById(1) } returns item

        mockMvc.perform(get("/items/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(item.id))
    }

}