package com.example.personalbalancebackend.controller;

import com.example.personalbalancebackend.entity.TxCategory;
import com.example.personalbalancebackend.mapper.TransactionCategoryMapper;
import com.example.personalbalancebackend.model.TransactionCategoryDTO;
import com.example.personalbalancebackend.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Service
@RequestMapping("api/v1/tx_categories")
public class TransactionCategoryController {
    private TransactionService transactionService;

    public TransactionCategoryController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public List<TransactionCategoryDTO> getAllTransactionCategories(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "10") Integer size) {

        Pageable paging = PageRequest.of(page, size);

        List<TxCategory> categories = transactionService.getAllTransactionCategories(paging);
        return TransactionCategoryMapper.INSTANCE.toListDTO(categories);
    }
}
