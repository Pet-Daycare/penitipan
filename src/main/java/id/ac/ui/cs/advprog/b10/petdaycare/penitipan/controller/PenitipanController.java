package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanAdminResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanFindService;
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
    private final PenitipanFindService penitipanFindService;

    @GetMapping("/find/all")
    public ResponseEntity<List<PenitipanAdminResponse>> getAllOrder() {
        List<PenitipanAdminResponse> response;
        response = penitipanFindService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/{statusPenitipan}")
    public ResponseEntity<List<PenitipanAdminResponse>> getAllOrderByStatus(@PathVariable String statusPenitipan){
        List<PenitipanAdminResponse> response;
        response = penitipanFindService.findAllByStatus(StatusPenitipan.valueOf(statusPenitipan));
        return  ResponseEntity.ok(response);
    }


    @GetMapping("/find/me")
    public ResponseEntity<List<PenitipanUserResponse>> getAllUserOrder(@RequestBody PenitipanRequest penitipanRequest) {
        List<PenitipanUserResponse> response;
        response = penitipanFindService.findAllByUserId(penitipanRequest);
       return ResponseEntity.ok(response);
    }

    @GetMapping("/me/frontend")
    public ResponseEntity<List<PenitipanUserResponse>> getAllUserOrder(@RequestParam Integer userId) {
        List<PenitipanUserResponse> response;
        PenitipanRequest request = new PenitipanRequest();
        request.setUserId(userId);
        response = penitipanFindService.findAllByUserId(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Penitipan> createPenitipan(@RequestBody PenitipanRequest penitipanRequest) {
        Penitipan response = penitipanService.create(penitipanRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Penitipan> getPenitipanById(@PathVariable Integer id) {
        Penitipan response = penitipanFindService.findPenitipanById(id);
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

    @PutMapping("/verify/{id}")
    public ResponseEntity<Penitipan> verifyPenitipan(@PathVariable Integer id ) {
        Penitipan response = penitipanService.verifyPayment(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/ambil/{id}")
    public ResponseEntity<Penitipan> pengambilanHewan(@PathVariable Integer id){
        Penitipan response = penitipanService.ambilHewan(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("pay/complete/{id}")
    public ResponseEntity<Penitipan> payComplete(@PathVariable Integer id){
        Penitipan response = penitipanService.payComplete(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("cancel/{id}")
    public ResponseEntity<Penitipan> cancel(@PathVariable Integer id){
        Penitipan response = penitipanService.cancel(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("complete/{id}")
    public ResponseEntity<Penitipan> complete(@PathVariable Integer id){
        Penitipan response = penitipanService.complete(id);
        return ResponseEntity.ok(response);
    }

}

