package ru.grigan.baraban.controler;

import org.springframework.web.bind.annotation.*;
import ru.grigan.baraban.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/message")
public class MessageController {
    private AtomicInteger counter = new AtomicInteger(3);
    private List<Map<String, String>> messages = new ArrayList<>() {
        {
            add(new HashMap<>() {{
                put("id", "1");
                put("text", "First message");
            }});
            add(new HashMap<>() {{
                put("id", "2");
                put("text", "Second message");
            }});
            add(new HashMap<>() {{
                put("id", "3");
                put("text", "Third message");
            }});
        }
    };

    @GetMapping
    public List<Map<String, String>> getMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessageById(@PathVariable String id) {
        return getMessageFromDB(id);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter.incrementAndGet()));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id,
                                      @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDB = getMessageFromDB(id);
        messageFromDB.putAll(message);
        messageFromDB.put("id", id);
        return messageFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> messageFromDB = getMessageFromDB(id);
        messages.remove(messageFromDB);
    }

    private Map<String, String> getMessageFromDB(String id) {
        return messages
                .stream()
                .filter(m -> m.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
