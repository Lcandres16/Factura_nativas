package com.UV_Shield.Factura_nativas.mapper

import com.UV_Shield.Factura_nativas.dto.ClientDto
import com.UV_Shield.Factura_nativas.model.Client
import com.UV_Shield.Factura_nativas.repository.ClientRepository
import org.springframework.stereotype.Component

@Component
class ClientMapper (private val clientRepository: ClientRepository){

    fun mapToDto(client: Client): ClientDto {
        return ClientDto(
            client.id,
            client.nui,
            client.full_name,
            client.address,
            client.email
        )
    }

    fun listDto(clients: List<Client>): List<ClientDto> {
        return clients.map { mapToDto(it) }
    }
}
