#!/bin/bash 
files="*.png"
regex="TNTx(.*)" # put the regex in a variable because some patterns won't work if included literally
for f in *.png    # unquoted in order to allow the glob to expand
do
    if [[ $f =~ $regex ]]
    then
        name="${BASH_REMATCH[1]}"
        echo "${name}"    # concatenate strings
        mv "TNTx${name}" "tnt_x${name}"    # same thing stored in a variable
    else
        echo "$f doesn't match" >&2 # this could get noisy if there are a lot of non-matching files
    fi
done
