# AdventOfCode2022

Welcome to the Advent of Code[^aoc] Kotlin project created by [yolgie][github] as prep for aoc2024.

Contains:
- Automatically downloading the input of the day
  - override in main file if you want to solve another day
  - input files in .gitignore
- Only 1 file per day (for both pt1 & 2)
  - flag in file what to solve for
- generic sample input by defining constants (lists for multiple) in the solution instead of having to create files for that.
- submit automatically if flag in file is set and tests run through
- Automatically generate the file for the day from a template if it does not exist already.

Ideas: 
- run the tests automatically for the sample input on change in file
- for autosubmit show nice error message in log if fail or open browser if success

Originally created using the [Advent of Code Kotlin Template][template] delivered by JetBrains but actually so heavily modified it looks nothing like the original anymore 😅.

[^aoc]:
    [Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
    Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
    You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com
[github]: https://github.com/yolgie
[kotlin]: https://kotlinlang.org
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template
