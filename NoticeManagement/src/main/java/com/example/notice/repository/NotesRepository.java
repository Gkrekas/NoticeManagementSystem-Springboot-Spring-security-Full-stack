package com.example.notice.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.notice.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>{
	@Query("from Notes as n where n.user.id=:uid")
	Page<Notes> findNotesByUser(@Param ("uid") int uid, Pageable p);
}
