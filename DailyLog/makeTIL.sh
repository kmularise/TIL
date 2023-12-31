#!/bin/bash

# Check if the template file exists
template_file="template.md"

if [ ! -f "$template_file" ]; then
    echo "Error: Template file 'template.md' not found!"
    exit 1
fi

# Get the current date in the format you desire
current_date=$(date +"%Y%m%d")
current_month=$(date +"%Y%m")

# Create a new file with the current date in the filename
new_file="${current_date}.md"
current_dir="Y${current_month}"

# Check if the current month directory exists, and create it if not
if [ ! -d "$current_dir" ]; then
    echo "Creating current month directory: ${current_dir}"
    mkdir "$current_dir"
fi

# Check if the new file already exists in the current month directory
if [ -f "$current_dir/$new_file" ]; then
    echo "Error: New file already exists"
    exit 1
fi

# Replace {DATE} in the template file with the current date
sed "s/{DATE}/$current_date/g" "$template_file" > "$current_dir/$new_file"

echo "New file '$new_file' created with the current date."