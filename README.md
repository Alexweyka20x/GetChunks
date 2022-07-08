# Get Chunks Mod

##Tips

You can stop your command using ```/get stop``` command

## How to use

Create new world and write ```/get blocks <filename>``` command<br>
Path to file must be ```getblocks/<filename>.txt```

First line contains count of blocks <br>
Each new line contains position of block

Example of file: <br>
```text
3
100 32 100
134 50 104
324 230 2
```

Result will be in ```getblocks/<filename>-result.txt```

Example of result: <br>
```text
stone
dirt
air
```
