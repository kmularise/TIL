#!/bin/bash

# Check if the template file exists
template_file="template.md"

if [ ! -f "$template_file" ]; then
    echo "Error: Template file 'template.md' not found!"
    exit 1
fi

# Get the current date in the format you desire
current_date=$(date +"%Y%m%d")

# Create a new file with the current date in the filename
new_file="${current_date}.md"

if [ -f "$new_file" ]; then
    echo "Error: new file already exits"
    exit 1
fi

# Replace {DATE} in the template file with the current date
sed "s/{DATE}/$current_date/g" "$template_file" > "$new_file"

echo "New file '$new_file' created with the current date."