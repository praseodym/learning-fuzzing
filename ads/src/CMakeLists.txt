
set (CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR})

file(GLOB sources "*.cpp")

foreach(source ${sources})
	get_filename_component(exec ${source} NAME_WE)
	add_executable(${exec} ${source})
	target_link_libraries(${exec} common ${libs})
endforeach()

