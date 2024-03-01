package com.UV_Shield.Factura_nativas.repository



import com.UV_Shield.Factura_nativas.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository: JpaRepository<Client, Long?> {
    fun findById (id: Long?): Client?
    fun findByAddress(address: String?): List<Client>
    @Query(nativeQuery = true, name = "Client.findClientsWithHighInvoices")
    fun findClientsWithHighInvoices(): List<Client>

}