package com.example.parser.dao;

import com.example.parser.model.Doc;
import java.util.List;

public interface DocumentDao {

	List<Doc> getAll();

	void addDocument(Doc doc);

}
