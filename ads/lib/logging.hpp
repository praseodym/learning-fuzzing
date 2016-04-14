#pragma once

#include <chrono>
#include <iostream>

struct timer{
	using clock = std::chrono::high_resolution_clock;
	using time = std::chrono::time_point<clock>;
	using seconds = std::chrono::duration<double>;

	std::string name;
	time begin;
	bool active = true;

	timer(std::string n)
	: name(n)
	, begin(clock::now())
	{
		std::cerr << name << std::endl;
	}

	void stop(){
		if(!active) return;
		time end = clock::now();
		std::cerr << "* " << from_duration(end - begin) << '\t' << name << std::endl;
		active = false;
	}

	~timer(){
		stop();
	}

	static double from_duration(seconds s){
		return s.count();
	}
};

// has same signature, but does not log :)
struct silent_timer {
	silent_timer(std::string){}
	void stop();
};
