package com.example.demo.contoroller;

import com.example.demo.entity.BoardEntity;
import com.example.demo.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<List<BoardEntity>> search(@RequestParam String category) {
        List<BoardEntity> boardEntities = boardService.search(category);
        return new ResponseEntity<>(boardEntities, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<BoardEntity>> findById(@RequestParam Long serverId) {
        List<BoardEntity> boardEntities = boardService.findByServerId(serverId);
        return new ResponseEntity<>(boardEntities, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BoardEntity> update(@RequestBody String boardData) throws JsonProcessingException {
        BoardEntity boardEntity = boardService.saveUpdate(boardData);
        return new ResponseEntity<>(boardEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long boardId){
        boardService.delete(boardId);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardEntity> showDetail(@PathVariable Long boardId){
        Optional<BoardEntity> boardEntityOptional = boardService.findByBoardId(boardId);
        if (boardEntityOptional.isPresent()){
            BoardEntity boardEntity = boardEntityOptional.get();
            return new ResponseEntity<>(boardEntity, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 또는 다른 상태 코드를 사용
        }
    }


}
