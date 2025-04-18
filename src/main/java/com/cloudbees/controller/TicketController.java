package com.cloudbees.controller;

import com.cloudbees.model.TicketPurchaseRequest;
import com.cloudbees.model.TicketPurchaseResponse;
import com.cloudbees.model.UpdateSeatRequest;
import com.cloudbees.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<TicketPurchaseResponse> purchaseTicket(@RequestBody @Valid TicketPurchaseRequest request) {
        TicketPurchaseResponse response = ticketService.purchaseTicket(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/receipt/{ticketId}")
    public ResponseEntity<TicketPurchaseResponse> getReceipt(@PathVariable("ticketId") String ticketId) {
        TicketPurchaseResponse response = ticketService.getReceipt(ticketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{ticketId}")
    public ResponseEntity removeUserByTicketId(@PathVariable("ticketId") String ticketId) {
        ticketService.removeUser(ticketId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/change-seat/{ticketId}")
    public ResponseEntity changeSeat(@PathVariable("ticketId") String ticketId, @RequestBody @Valid UpdateSeatRequest request) {
        ticketService.updateUserSeat(ticketId, request);
        return new ResponseEntity(HttpStatus.OK);
    }
}
