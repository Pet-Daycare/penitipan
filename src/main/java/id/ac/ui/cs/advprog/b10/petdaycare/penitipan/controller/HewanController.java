package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/hewan")
@RequiredArgsConstructor
public class HewanController {
    @Autowired
    private final HewanService hewanService;

    @GetMapping("/all")
    public ResponseEntity<List<Hewan>> getAllHewan() {
        List<Hewan> response = null;
        response = hewanService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Hewan> getHewanById(@PathVariable Integer id) {
        Hewan response = null;
        response = hewanService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Hewan> addHewan(@RequestBody HewanRequest request) {
        Hewan response = null;
        response = hewanService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hewan> putHewan(@PathVariable Integer id, @RequestBody HewanRequest request) {
        Hewan response = null;
        response = hewanService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHewan(@PathVariable Integer id) {
        hewanService.delete(id);
        return ResponseEntity.ok(String.format("Deleted Hewan with id %d", id));
    }
}
