package mk.ukim.finki.emt.rent_room_application.events;

import mk.ukim.finki.emt.rent_room_application.listeners.HostCreatedEvent;
import mk.ukim.finki.emt.rent_room_application.service.domain.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandler {

    @Autowired
    private final HostService hostService;

    public HostEventHandler(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onProductCreated(HostCreatedEvent event) {
        this.hostService.refreshMaterializedView();
    }



}
