package com.codebyte.bugservice.service;

import com.codebyte.bugservice.VO.Product;
import com.codebyte.bugservice.VO.User;
import com.codebyte.bugservice.configuration.WebClientConfig;
import com.codebyte.bugservice.dto.BugRequestDTO;
import com.codebyte.bugservice.dto.BugResponseDTO;
import com.codebyte.bugservice.entity.Bug;
import com.codebyte.bugservice.mapper.BugMapper;
import com.codebyte.bugservice.repository.BugRepository;
import com.codebyte.bugservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BugService {

    private final BugRepository bugRepository;
    private final BugMapper bugMapper;
    private final WebClientConfig webClient;

    public List<BugResponseDTO> getBugs() {
        log.info("Inside BugService getBugs()");
        List<Bug> bugs = bugRepository.findAll();

        List<String> idsAssignBy = bugs.stream()
                .map(Bug::getAssignByUlid).collect(Collectors.toList());

        List<String> idsAssignTo = bugs.stream()
                .map(Bug::getAssignToUlid).collect(Collectors.toList());

        List<String> productCodes = bugs.stream()
                .map(Bug::getProductUlid).collect(Collectors.toList());

        User[] usersAssignBy = webClient.webClientBuilder().build().get()
                    .uri("http://USER-SERVICE/users",
                            uriBuilder -> uriBuilder.queryParam("id", idsAssignBy).build())
                    .retrieve()
                    .bodyToMono(User[].class)
                    .block();

        User[] usersAssignTo = webClient.webClientBuilder().build().get()
                .uri("http://USER-SERVICE/users",
                        uriBuilder -> uriBuilder.queryParam("id", idsAssignTo).build())
                .retrieve()
                .bodyToMono(User[].class)
                .block();

        Product[] products = webClient.webClientBuilder().build().get()
                .uri("http://PRODUCT-SERVICE/products",
                        uriBuilder -> uriBuilder.queryParam("code", productCodes).build())
                .retrieve()
                .bodyToMono(Product[].class)
                .block();

        List<BugResponseDTO> bugResponseDTOs = bugMapper.bugsToBugResponseDTOs(bugs,usersAssignBy,usersAssignTo,products);

        return bugResponseDTOs;
    }

    public void saveBug(BugRequestDTO bugRequestDTO) {
        log.info("Inside BugService saveBug()");
        Bug bug = bugMapper.bugDTOToBug(bugRequestDTO);
        bug.setCreatedDate(LocalDate.now());
        bugRepository.save(bug);
    }

    public void deleteBug(Long bugId) {
        log.info("Inside BugService deleteBug BugId: {}",bugId);
        bugRepository.deleteById(bugId);
    }

    public void updateBug(BugRequestDTO bugRequestDTO,Long bugId) {
          log.info("Inside BugService updateBug BugId: {}",bugId);
            Optional<Bug> bug = bugRepository.findById(bugId);
            Bug updateBug = getUpdateBug(bug.get(), bugRequestDTO);
            bugRepository.save(updateBug);
    }

    private Bug getUpdateBug(Bug bugToUpdate, BugRequestDTO bugRequestDTO){
        Bug bug = bugMapper.bugDTOToBug(bugRequestDTO);
        bugToUpdate.setName(bug.getName());
        bugToUpdate.setDescription(bug.getDescription());
        bugToUpdate.setSeverity(bug.getSeverity());
        bugToUpdate.setStatus(bug.getStatus());
        bugToUpdate.setProductUlid(bug.getProductUlid());
        bugToUpdate.setAssignByUlid(bug.getAssignByUlid());
        bugToUpdate.setAssignToUlid(bug.getAssignToUlid());
        bugToUpdate.setCreatedDate(bug.getCreatedDate());
        bugToUpdate.setUpdatedDate(LocalDate.now());
        return bugToUpdate;
    }
}
