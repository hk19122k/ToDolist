package com.example.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.model.ToDo;
import com.example.todolist.repo.ToDoRepo;

@Service
public class ToDoService {

	
	@Autowired
	private ToDoRepo repo;
	
	public List<ToDo> getAllToDoItems()
	{
     ArrayList<ToDo> todoList = new ArrayList<>();
     repo.findAll().forEach(todo -> todoList.add(todo));
     return todoList;
	}
	
	public ToDo getToDoItemsById(Long id)
	{ 
		return repo.findById(id).get();
	}
	
   public	boolean updateStatus(Long id)
	{
	   ToDo todo = getToDoItemsById(id);
	   todo.setStatus("Completed");
	   
	   return saveOrUpdateToDoItem(todo);
	}
   
	public boolean saveOrUpdateToDoItem(ToDo todo)
	{
	   ToDo updateObj =  repo.save(todo);
	   
	   if(getToDoItemsById(updateObj.getId()) != null)
	   {
		   return true;
	   }
	   
	   return false;	   
	}
	
	public boolean deleteToDoItem(Long id)
	{
		repo.deleteById(id);
		
		if(getToDoItemsById(id) == null)
		{
			return true;
		}
		return false;
	}
	
}
