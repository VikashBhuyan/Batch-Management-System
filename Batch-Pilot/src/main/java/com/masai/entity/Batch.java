package com.masai.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Batch {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int batch_id;
@Column(name = "course_name", nullable = false, unique = true, length = 50)
private String course_name;
@Column(name = "no_seats" , nullable = false )
private int number_of_seats;
@Column
private LocalDate start;
private double duration;

@ManyToMany(mappedBy="batchList", cascade=CascadeType.ALL ,fetch = FetchType.LAZY)
private List<Faculty> facultyList;
public Batch() {
	super();
	// TODO Auto-generated constructor stub
}
public Batch(String course_name, int number_of_seats, LocalDate start, double duration, List<Faculty> facultyList) {
	super();
	this.course_name = course_name;
	this.number_of_seats = number_of_seats;
	this.start = start;
	this.duration = duration;
	this.facultyList = facultyList;
}
public int getBatch_id() {
	return batch_id;
}
public void setBatch_id(int batch_id) {
	this.batch_id = batch_id;
}
public String getCourse_name() {
	return course_name;
}
public void setCourse_name(String course_name) {
	this.course_name = course_name;
}
public int getNumber_of_seats() {
	return number_of_seats;
}
public void setNumber_of_seats(int number_of_seats) {
	this.number_of_seats = number_of_seats;
}
public LocalDate getStart() {
	return start;
}
public void setStart(LocalDate start) {
	this.start = start;
}
public double getDuration() {
	return duration;
}
public void setDuration(double duration) {
	this.duration = duration;
}
public List<Faculty> getFacultyList() {
	return facultyList;
}
public void setFacultyList(List<Faculty> facultyList) {
	this.facultyList = facultyList;
}
@Override
public String toString() {
    return "Batch [id=" + batch_id + ", name=" + course_name + "]";
}
}



	

