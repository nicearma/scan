import { ScanPage } from './app.po';

describe('scan App', function() {
  let page: ScanPage;

  beforeEach(() => {
    page = new ScanPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
