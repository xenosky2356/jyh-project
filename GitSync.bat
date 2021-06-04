:loop
    setlocal
    @set YEAR=%date:~0,4%
    @set MONTH=%date:~5,2%
    @set DAY=%date:~8,2%
    @set HOUR=%time:~0,2%
    @set MINUTE=%time:~3,2%
    @set SECOND=%time:~6,2%
    
    @set timestamp=%YEAR%/%MONTH%/%DAY% %HOUR%:%MINUTE%:%SECOND%

    git pull origin master

    git add *

    git commit -m "%timestamp% Commit & Update"

    git push -u origin master

    timeout 60

    goto loop