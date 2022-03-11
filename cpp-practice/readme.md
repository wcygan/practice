# C++ Practice

Following https://raymii.org/s/tutorials/Cpp_project_setup_with_cmake_and_unit_tests.html to setup

I should probably read this https://google.github.io/googletest/

## Build

1. Install googletest

```
$ cd build
$ git clone https://github.com/google/googletest/
$ cd ..
```

2. Compile everything
```
$ cd build
$ cmake .. -DCMAKE_BUILD_TYPE=Debug -G "Unix Makefiles" 
```

3. Run the program
```
$ ./src/ExampleProject_run
```

4. Test the program
```
$ ./tst/ExampleProject_tst 
<!-- or -->
$ make ExampleProject_tst; tst/ExampleProject_tst 
```

