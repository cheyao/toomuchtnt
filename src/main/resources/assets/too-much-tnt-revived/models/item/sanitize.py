#!/bin/env python3
"""
{
    "parent": "toomuchtnt:block/EasterEgg",
    "display": {
        "thirdperson": {
            "rotation": [ 10, -45, 170 ],
            "translation": [ 0, 1.5, -2.75 ],
            "scale": [ 0.375, 0.375, 0.375 ]
        }
    }
}
"""

import json
from pathlib import Path
import re
pattern = re.compile(r'(?<!^)(?=[A-Z])')
pattern2 = re.compile(r'TNT')

lowtnt = lambda name : pattern2.sub('_tnt', name).lower();
camelcase = lambda name : pattern.sub('_', name).lower();

pathlist = Path(".").glob('*.json')
for path in pathlist:
    filename = str(path);

    newFilename = lowtnt(filename);
    newFilename = camelcase(newFilename);
    print(newFilename);

