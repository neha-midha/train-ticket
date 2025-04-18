package com.cloudbees.service;

import com.cloudbees.model.TicketPurchaseRequest;
import com.cloudbees.model.TicketPurchaseResponse;
import com.cloudbees.model.UpdateSeatRequest;

public interface TicketService {

    TicketPurchaseResponse purchaseTicket(TicketPurchaseRequest request);

    TicketPurchaseResponse getReceipt(String ticketId);

    void removeUser(String ticketId);

    void updateUserSeat(String ticketId, UpdateSeatRequest request);
}
