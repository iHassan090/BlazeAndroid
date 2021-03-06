package com.techworx.blaze.widgets.patternedittext.Utils;


public final class Span {

    private int mOffset;
    private int mLength;

    public Span(int offset, int length) {
        this.mOffset = offset;
        this.mLength = length;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        this.mOffset = offset;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        this.mLength = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Span span = (Span) o;

        if (mLength != span.mLength) return false;
        return mOffset == span.mOffset;
    }

    @Override
    public int hashCode() {
        int result = mOffset;
        result = 31 * result + mLength;
        return result;
    }
}