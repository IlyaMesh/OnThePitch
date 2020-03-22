import {Component, OnInit} from '@angular/core';
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {UploadFileService} from "../service/upload-file.service";

@Component({
  selector: 'app-upload-files',
  templateUrl: './upload-files.component.html',
  styleUrls: ['./upload-files.component.css']
})
export class UploadFilesComponent implements OnInit {

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';


  constructor(private uploadService: UploadFileService) { }

  ngOnInit(): void {

  }
  selectFile(event) {
    this.selectedFiles = event.target.files;
    if(this.selectedFiles.length>1){
      this.message = 'Select only one file';
      this.selectedFiles = undefined;
    }
    else{
      this.message = '';
      this.currentFile = this.selectedFiles.item(0);
    }


  }
  upload() {
    this.progress = 0;

    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

}
