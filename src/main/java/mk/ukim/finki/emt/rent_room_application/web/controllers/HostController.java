package mk.ukim.finki.emt.rent_room_application.web.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rent_room_application.dto.CreateHostDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayHostDto;
import mk.ukim.finki.emt.rent_room_application.model.projection.HostProjection;
import mk.ukim.finki.emt.rent_room_application.model.views.HostPerCountryView;
import mk.ukim.finki.emt.rent_room_application.repository.HostPerCountryViewRepository;
import mk.ukim.finki.emt.rent_room_application.repository.HostRepository;
import mk.ukim.finki.emt.rent_room_application.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts", description = "Host API")

public class HostController {

    private final HostApplicationService hostApplicationService;
    private final HostPerCountryViewRepository hostPerCountryViewRepository;
    private  final HostRepository hostRepository;

    public HostController(HostApplicationService hostApplicationService, HostPerCountryViewRepository hostPerCountryViewRepository, HostRepository hostRepository) {
        this.hostApplicationService = hostApplicationService;
        this.hostPerCountryViewRepository = hostPerCountryViewRepository;
        this.hostRepository = hostRepository;
    }


    @GetMapping
    public List<DisplayHostDto> findAll() {
        return this.hostApplicationService.findAll();
    }

    @GetMapping("/{id}")
//    @Operation(summary = "Returns an author by its ID")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return this.hostApplicationService.findById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new host")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto hostDto) {
        System.out.println("Received host: " + hostDto);
        return this.hostApplicationService.save(hostDto)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates a host record")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto hostDto) {
        return this.hostApplicationService.update(id, hostDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a host by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-country")
    public List<HostPerCountryView> getHostsByCountry() {
        return hostPerCountryViewRepository.findAll();
    }
    @GetMapping("/names")
    public List<HostProjection> getHostNames() {
        return hostRepository.findAllBy();
    }


}
