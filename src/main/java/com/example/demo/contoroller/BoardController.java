package com.example.demo.contoroller;

import com.example.demo.dto.BoardResponseDTO;
import com.example.demo.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "/write", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody String boardData) throws JsonProcessingException {

        boardService.save(boardData);
        return ResponseEntity.ok("저장 성공");
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardResponseDTO>> search(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) String gender,
                                                    @RequestParam(required = false) String clothCategory,
                                                    @RequestParam(required = false) String place) throws JsonProcessingException {
        System.out.println("들어옴");
        List<BoardResponseDTO> boardResponseDTOList = boardService.search(title, gender, clothCategory, place);
        return new ResponseEntity<>(boardResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/{serverId}")
    public ResponseEntity<List<BoardResponseDTO>> findById(@PathVariable String serverId) throws JsonProcessingException {
        List<BoardResponseDTO> boardResponseDTOList = boardService.findByServerId(Long.parseLong(serverId));
        return new ResponseEntity<>(boardResponseDTOList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody String boardData) throws JsonProcessingException {
        System.out.println(boardData);
        boardService.saveUpdate(boardData);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{boardId}")
    public void delete(@PathVariable String boardId){
        boardService.delete(Long.parseLong(boardId));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> showDetail(@PathVariable String boardId) throws JsonProcessingException {
        BoardResponseDTO boardResponseDTO = boardService.findByBoardId(Long.parseLong(boardId));
        return new ResponseEntity<>(boardResponseDTO, HttpStatus.OK);
    }

}
