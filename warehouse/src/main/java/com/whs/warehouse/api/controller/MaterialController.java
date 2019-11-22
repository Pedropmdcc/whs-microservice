package com.whs.warehouse.api.controller;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/material")
public interface MaterialController {


    /**
     * Add material to the database
     *
     * @param materialRequest Material Request object with data
     * @return Response with HTTP Status.
     */
    @PostMapping
    ResponseEntity<MaterialResponse> save(@RequestBody final MaterialRequest materialRequest);

    /**
     * Returns all Materials.
     * @return Response with a List of all Materials.
     */
    @GetMapping
    ResponseEntity<List<MaterialResponse>> getAll();

    /**
     * Search for a Material with specific href.
     * @param id of the Material.
     * @return ResponseEntity with Response Status.
     */
    @GetMapping(value = "/{id}")
    ResponseEntity<MaterialResponse> getById(@PathVariable("id") final String id);

    /**
     * Delete Material from the database
     * @param id of the Material
     * @return ResponseEntity with Response Status.
     */
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") final String id);


    /**
     * Update Material from the database
     * @param materialRequest Material Request object with data
     * @param id of the Material that will be update
     * @return ResponseEntity with Response Status.
     */
    @PutMapping(value = "/{id}")
    ResponseEntity<MaterialResponse> update(@RequestBody final MaterialRequest materialRequest, @PathVariable("id") final String id);
}
