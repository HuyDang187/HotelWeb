package com.hotelky4.projecthotel.controller;

import com.hotelky4.projecthotel.entity.Room;
import com.hotelky4.projecthotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService theRoomController){

        roomService = theRoomController;
    }
    //show room list - user
    @GetMapping("/list")
    public String listRoom(Model theModel){
        List<Room> theRooms = roomService.findAll();
        theModel.addAttribute("rooms",theRooms);
        return "rooms.html";
    }
    //search by title and type
    @GetMapping("/search-room")
    public String listSearch(@RequestParam(value = "titleRoomKey",required = false)String titleRoomKey,
                             @RequestParam(value = "typeRoomKey",required = false)String typeRoomKey,
                             Model model){

        List<Room> foundRooms = roomService.searchRoomsByTitleAndType(titleRoomKey,typeRoomKey);
        model.addAttribute("rooms",foundRooms);
        return "rooms.html";
    }
    //get room by Id
    @RequestMapping("/room")
    public String getRoomById(@RequestParam(value = "roomId")int theId , Model model){
        Room theRoom = roomService.findById(theId);
        model.addAttribute("room",theRoom);
        return "rooms-single.html";
    }



    //show room list - admin
    @GetMapping("/admin/list")
    public String getListRoom(Model theModel) {
        List<Room> theRooms = roomService.findAll();
        theModel.addAttribute("rooms",theRooms);
        return "/admin/room/list-room.html";
    }

    //add room
    @GetMapping("/admin/showFormForAdd")
    public String showFormForAdd(Model model){
        Room newRoom = new Room();
        model.addAttribute("room",newRoom);


        return "/admin/room/room-form";
    }

    @PostMapping("/admin/save")
    public String saveRoom(@ModelAttribute("room")Room theRoom,@RequestParam("image") MultipartFile imageFile){

        if (!imageFile.isEmpty()) {
            try {
                addImg(theRoom,imageFile);
                roomService.save(theRoom);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        roomService.save(theRoom);
        return "redirect:/rooms/admin/list";
    }
    //update Room
    @GetMapping("/admin/showFormForUpdate")
    public String showFormForAdd(@RequestParam("roomId") int theId, Model theModel){
        //get room from service
        Room theRoom = roomService.findById(theId);

        theModel.addAttribute("room",theRoom);
        return "/admin/room/room-form-update";
    }
    @PostMapping("/admin/update")
    public String updateRoom(@ModelAttribute("room")Room theRoom,@RequestParam("image") MultipartFile imageFile){

        if(!imageFile.isEmpty()) {
            try {
                addImg(theRoom,imageFile);
                roomService.save(theRoom);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        roomService.save(theRoom);
        return "redirect:/rooms/admin/list";
    }
    //delete room
    @GetMapping("/admin/delete")
    public String delete(@RequestParam("roomId")int theId){
        roomService.deleteById(theId);
        return "redirect:/rooms/admin/list";
    }
    public Room addImg(Room theRoom,MultipartFile imageFile) throws IOException {
        String originalFileName = imageFile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = theRoom.getRoomTitle().replaceAll("\\s+","_")+ UUID.randomUUID()+fileExtension;
        String staticDirectory = "src/main/resources/static";
        String imageDirectory = "/images/roomImg/";
        Path imageFilePath = Paths.get(staticDirectory + imageDirectory + fileName);
        Files.copy(imageFile.getInputStream(),imageFilePath);
        theRoom.setRoomImg(fileName);
        return theRoom;
    }

    @RequestMapping("/booking")
    public String booking(Model model){
        return "booking";
    }
    @RequestMapping("/room/{id}")
    public String room(@PathVariable("id") Room room,Model model){

        return "room";
    }
}
