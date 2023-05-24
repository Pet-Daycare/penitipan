package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Penitipan")
@RequiredArgsConstructor
public class PenitipanController {
    private final PenitipanService penitipanService;

    @GetMapping("/all")
    public ResponseEntity<List<PenitipanAdminResponse>> getAllOrder() {
        List<PenitipanAdminResponse> response = null;
        response = penitipanService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<PenitipanUserResponse>> getAllUserOrder(@RequestBody PenitipanRequest penitipanRequest) {
        List<PenitipanUserResponse> response = null;
        response = penitipanService.findAllByUserId(penitipanRequest);
       return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Penitipan> createPenitipan(@RequestBody PenitipanRequest penitipanRequest) {
        Penitipan response = penitipanService.create(penitipanRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Penitipan> updateOrder(@PathVariable Integer id, @RequestBody PenitipanRequest penitipanRequest) {
        Penitipan response = penitipanService.update(id, penitipanRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        penitipanService.delete(id);
        return ResponseEntity.ok(String.format("Deleted Order with id %d", id));
    }

    @PatchMapping("/verify/{id}")
    public ResponseEntity<Penitipan> verifyPenitipan(@PathVariable Integer id ) {
        Penitipan response = penitipanService.verify(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/ambil/{id}")
        public ResponseEntity<Penitipan> pengambilanHewan(@PathVariable Integer id){
        Penitipan response = penitipanService.ambilHewan(id);
        return ResponseEntity.ok(response);
    }

}

