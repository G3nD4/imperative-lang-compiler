# Imperative Language Compiler

## Project Overview
The Imperative Language Compiler is a project designed to parse and compile a custom imperative programming language using ANTLR (ANother Tool for Language Recognition). This project aims to provide a robust framework for understanding and processing imperative language constructs, enabling further development of language features and optimizations.

## Setup Guide

To set up the project, follow these steps:

1. **Navigate to the main source directory**:
   ```bash
   cd src/main
   ```

2. **Generate the ANTLR parser and lexer**:
   ```bash
   antlr -o antlrTree MyLang.g4
   ```

3. **Change to the output directory**:
   ```bash
   cd antlrTree
   ```

4. **Compile the generated Java files**:
   ```bash
   javac MyLang*.java
   ```

## Additional Information
Make sure you have ANTLR installed and properly configured in your environment. You can find more information about ANTLR [here](https://www.antlr.org/).

For any issues or contributions, please refer to the project's issue tracker or contact the maintainers.